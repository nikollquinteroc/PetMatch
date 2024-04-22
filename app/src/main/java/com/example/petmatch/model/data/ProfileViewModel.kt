package com.example.petmatch.model.data
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var user = mutableStateOf(Users())

    fun loadUserProfile() {
        val usersCollection = FirebaseFirestore.getInstance().collection("users")
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        viewModelScope.launch {
            try {
                val userDocument = usersCollection.document(currentUserId!!).get().await()
                if (userDocument.exists()) {
                    val userData = userDocument.toObject(Users::class.java)
                    user.value = userData ?: Users()
                } else {
                }
            } catch (e: Exception) {
            }
        }
    }

    fun loadUserForEdit(userId: String) {
        val usersCollection = FirebaseFirestore.getInstance().collection("users")

        viewModelScope.launch {
            try {
                val userDocument = usersCollection.document(userId).get().await()
                if (userDocument.exists()) {
                    val userData = userDocument.toObject(Users::class.java)
                    user.value = userData ?: Users()
                } else {
                    //
                }
            } catch (e: Exception) {
                // Manejar errores
            }
        }
    }

    fun updateUserProfile(updatedUser: Users) {
        val usersCollection = FirebaseFirestore.getInstance().collection("users")

        val userId = updatedUser.id

        if (userId != null) {
            val userDocument = usersCollection.document(userId)

            val updates = hashMapOf<String, Any?>()

            updates["firstName"] = updatedUser.firstName
            updates["lastName"] = updatedUser.lastName
            updates["gender"] = updatedUser.gender
            updates["img_url"] = updatedUser.img_url

            userDocument.update(updates)
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->
                }
        } else {
            //
        }
    }

}
