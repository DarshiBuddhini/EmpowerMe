package com.example.empowerme

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class EditProductActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        val db = Firebase.firestore

        val productId = intent.getStringExtra("productId")
        val pcategory = intent.getStringExtra("productCategory")
        val description = intent.getStringExtra("description")
        val pName = intent.getStringExtra("productName")
        val price = intent.getStringExtra("ProductPrice")

        val docRef = productId?.let { db.collection("products").document(it) }

        val prodCatEditText = findViewById<EditText>(R.id.edtProCat3)
        val prodesEditText = findViewById<EditText>(R.id.edtProDes4)
        val proNameEditText = findViewById<EditText>(R.id.editProName)
        val proPriceEditText = findViewById<EditText>(R.id.edtProPrice)
        val saveButton = findViewById<Button>(R.id.button5)
        val editImageButton = findViewById<Button>(R.id.editImage)

        prodCatEditText.hint = pcategory
        prodesEditText.hint = description
        proNameEditText.hint = pName
        proPriceEditText.hint = price

        editImageButton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PICK_IMAGE_REQUEST)
            } else {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }
        }

        saveButton.setOnClickListener {
            val pcategory = prodCatEditText.text.toString()
            val description = prodesEditText.text.toString()
            val pName = proNameEditText.text.toString()
            val price = proPriceEditText.text.toString()

            val data = hashMapOf(
                "productCategory" to pcategory,
                "description" to description,
                "productName" to pName,
                "ProductPrice" to price,
            "productImage" to imageUri.toString()
            )

            if (docRef != null) {
                docRef.update(data as Map<String, Any>)
                    .addOnSuccessListener {
                        Toast.makeText(this, " Updated successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, EditproductSuccessActivity::class.java)
                        startActivity(intent)
                        finish()
                        if (imageUri != null) {
                            val storageRef = Firebase.storage.reference.child("products/$productId.jpg")
                            storageRef.putFile(imageUri!!)
                                .addOnSuccessListener {
                                    storageRef.downloadUrl.addOnSuccessListener { url ->
                                        docRef.update("imageUrl", url.toString())
                                    }
                                }
                        }
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating : $e", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        val docRefDlt = productId?.let { db.collection("products").document(it) }
        val BtnDltProduct = findViewById<ImageButton>(R.id.BtnDltProduct)
        BtnDltProduct.setOnClickListener {
            if (docRefDlt != null) {
                docRefDlt.delete()
                    .addOnSuccessListener {
                        //Redirect to the RequestDeletedSuccess activity after deleting document
                        val intent = Intent(this, ProductDeleteSuccess::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating : $e", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        editImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
        val backtoBtn = findViewById<ImageButton>(R.id.button2)
        backtoBtn.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }
    }
}