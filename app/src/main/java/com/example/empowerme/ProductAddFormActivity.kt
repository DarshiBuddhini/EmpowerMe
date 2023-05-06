package com.example.empowerme
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class ProductAddFormActivity : AppCompatActivity() {

    private lateinit var productCategory: EditText
    private lateinit var pdescription: EditText
    private lateinit var productName: EditText
    private lateinit var productPrice: EditText
    private lateinit var btnProPublish: Button
    private lateinit var btnSelectProductImage: Button
    private lateinit var productImageView: ImageView

    val db = Firebase.firestore

    private var imageUri: Uri? = null
    private val storageRef = Firebase.storage.reference.child("product_images")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_add_form)

        val BB = findViewById<ImageButton>(R.id.backStoreBtn)
        BB.setOnClickListener {
            val intent = Intent(this, AddProductActivity::class.java)
            startActivity(intent)
        }

        productCategory = findViewById(R.id.edtProductCat)
        pdescription = findViewById(R.id.edtProductDes)
        productName = findViewById(R.id.productName)
        productPrice = findViewById(R.id.productPrice)
        btnProPublish = findViewById(R.id.btnProductAdd)
        btnSelectProductImage = findViewById(R.id.BtnAddImage)
        productImageView = findViewById(R.id.productImgView)

        btnSelectProductImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startForResult.launch(intent)
        }

        btnProPublish.setOnClickListener {

            val pCat = productCategory.text.toString().trim()
            val pName = productName.text.toString().trim()
            val pDes = pdescription.text.toString().trim()
            val pPrice = productPrice.text.toString().trim()

            if (pCat.isEmpty()) {
                Toast.makeText(this, "Please enter a product category", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pName.isEmpty()) {
                Toast.makeText(this, "Please enter a product name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pDes.isEmpty()) {
                Toast.makeText(this, "Please enter a product description", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (pPrice.isEmpty()) {
                Toast.makeText(this, "Please enter a product price", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (imageUri == null) {
                Toast.makeText(this, "Please select a product image", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val imageRef = storageRef.child("${System.currentTimeMillis()}.jpg")

            imageRef.putFile(imageUri!!)
                .addOnSuccessListener {
                    imageRef.downloadUrl.addOnSuccessListener { imageUrl ->

                        val productsMap = hashMapOf(
                            "productCategory" to pCat,
                            "productName" to pName,
                            "description" to pDes,
                            "ProductPrice" to pPrice,
                            "productImage" to imageUrl.toString()
                        )
                        db.collection("products")
                            .add(productsMap)
                            .addOnSuccessListener { documentReference ->
                                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                                val intent =
                                    Intent(this, AddProductSuccessActivity::class.java)
                                startActivity(intent)
                                finish()

                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed $e", Toast.LENGTH_LONG).show()
                            }
                    }
                }
        }
    }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                imageUri = result.data?.data
                productImageView.setImageURI(imageUri)
            }
        }
}
