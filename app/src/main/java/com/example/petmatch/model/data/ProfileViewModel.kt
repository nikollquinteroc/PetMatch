package com.example.petmatch.model.data

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch


class ProfileViewModel : ViewModel() {
    var user = mutableStateOf(Users())
    var imageUrl = mutableStateOf<String?>(null)

    fun loadUserProfile() {
        val usersCollection = FirebaseFirestore.getInstance().collection("users")
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        viewModelScope.launch {
            try {
                val userDocument = usersCollection.document(currentUserId!!).get().await()
                if (userDocument.exists()) {
                    val userData = userDocument.toObject(Users::class.java)
                    userData?.id = userDocument.id
                    user.value = userData ?: Users()
                    imageUrl.value = userData?.img_url

                }
            } catch (e: Exception) {
            }
        }
    }

    fun updateUserProfile(updatedUser: Users, imageUrl: String? = null) {
        val userId = updatedUser.id

        if (userId != null) {
            val usersCollection = FirebaseFirestore.getInstance().collection("users")
            val userDocument = usersCollection.document(userId)

            val updates = hashMapOf<String, Any?>()

            updates["firstName"] = updatedUser.firstName
            updates["lastName"] = updatedUser.lastName
            updates["gender"] = updatedUser.gender
            updates["img_url"] = imageUrl ?: updatedUser.img_url

            userDocument.update(updates)
                .addOnSuccessListener {
                    user.value = updatedUser
                    Log.d("ProfileViewModel", "User profile updated successfully")
                }
                .addOnFailureListener { e ->
                    Log.e("ProfileViewModel", "Error updating user profile", e)
                }
        } else {
            Log.e("ProfileViewModel", "User ID is null")
        }
    }

    fun uploadImageToStorage(imageUri: Uri, onUploadComplete: (String) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("${imageUri.lastPathSegment}")
        val uploadTask = imagesRef.putFile(imageUri)

        uploadTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                imagesRef.downloadUrl.addOnSuccessListener { downloadUri ->
                    val imageUrl = downloadUri.toString()
                    onUploadComplete(imageUrl)
                    val updatedUser = user.value.copy(img_url = imageUrl)
                    updateUserProfile(updatedUser, imageUrl)
                }
            }
        }
    }


}