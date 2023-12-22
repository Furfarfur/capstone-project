package com.example.capstone.ui.pages.upload

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import coil.compose.AsyncImage
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.capstone.R
import com.example.capstone.data.local.entity.RecipeEntity
import com.example.capstone.utils.getImageUri
import com.example.capstone.utils.reduceFileImage
import com.example.capstone.utils.uriToFile
import com.example.capstone.ui.component.JetButton
import com.example.capstone.ui.component.JetToast
import com.example.capstone.ui.component.JetTopBar
import com.example.capstone.ui.pages.recipe.RecipePage
import com.example.capstone.ui.pages.recipe.RecipeViewModel
import com.example.capstone.ui.theme.BluePrimary
import com.example.capstone.ui.theme.BlueText
import com.example.capstone.ui.theme.CapstoneTheme
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import com.example.capstone.utils.Result


class UploadPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    UploadPages()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun UploadPages(
    ) {
        Scaffold(
            topBar = { JetTopBar(context = LocalContext.current) }
        ) {
            UploadPagesContent(
                navigateToResult = {  }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun UploadPagesContent(
        modifier: Modifier = Modifier,
        navigateToResult: () -> Unit,
        context: Context = LocalContext.current,
    ) {
        var description by remember { mutableStateOf("") }

        var showDialog by remember {
            mutableStateOf(false)
        }

        var showLoading by remember {
            mutableStateOf(false)
        }


        var currentImageUri: Uri? = null

        var capturedImageUri by remember {
            mutableStateOf<Uri>(Uri.EMPTY)
        }

        val launcherCamera =
            rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
                capturedImageUri = currentImageUri!!
            }

        val launcherGallery = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                capturedImageUri = currentImageUri!!
            } else {
                Log.d("Photo Picker", "No Media Selected")
            }

        }


        fun startGallery() {
            launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        fun startCamera() {
            currentImageUri = context.getImageUri(context)
            launcherCamera.launch(currentImageUri)
        }

        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            if (it) {
                startCamera()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        color = Color.White,
                    )
            ) {
                Box(modifier = Modifier) {
                    Image(
                        painter = painterResource(id = R.drawable.background2profile),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .matchParentSize()
                            .fillMaxWidth()
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(top = 140.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
                        elevation = 10.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .padding(start = 20.dp, end = 20.dp)
                                .clip(RoundedCornerShape(20.dp)),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (capturedImageUri.path?.isNotEmpty() == true) {
                                AsyncImage(
                                    model = capturedImageUri,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentDescription = "LogoApp",
                                    contentScale = ContentScale.Fit
                                )
                            } else if (capturedImageUri.path?.isEmpty() == true) {
                                Image(
                                    painter = painterResource(id = R.drawable.photoupload),
                                    contentDescription = "",
                                    contentScale = ContentScale.FillBounds
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (showLoading) {
                            val infiniteTransition = rememberInfiniteTransition()

                            val boxSize = 420.dp
                            val animationDuration = 5000

                            val heightAnimation by infiniteTransition.animateValue(
                                initialValue = 0.dp,
                                targetValue = boxSize,
                                typeConverter = Dp.VectorConverter,
                                animationSpec = infiniteRepeatable(
                                    animation = keyframes {
                                        durationMillis = animationDuration
                                        0.dp at 0 // ms
                                        boxSize at animationDuration / 2
                                        0.dp at animationDuration using FastOutSlowInEasing
                                    }
                                    // Use the default RepeatMode.Restart to start from 0.dp after each iteration
                                ),
                            )
                            Spacer(modifier = Modifier.height(heightAnimation))
                            Divider(
                                thickness = 4.dp, color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                            )
                        }
                    }

                }


                if (showDialog) {
                    androidx.compose.material3.AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        title = {
                            androidx.compose.material3.Text(text = "Scan Berhasil")
                        },
                        icon = {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "checkCircle"
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
//                                    navigateToResult()
                                },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(
                                    MaterialTheme.colorScheme.primary
                                )
                            ) {
                                androidx.compose.material3.Text(
                                    text = "Yes",
                                    color = Color.Black
                                )
                            }
                        }
                    )
                }

                /*
                Button Gallery And Camera
             */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp, top = 20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    JetButton(
                        onClick = { startGallery() },
                        color = BluePrimary,
                        enabled = true,
                        label = "File",
                        modifier = Modifier.size(width = 160.dp, height = 50.dp)
                    )

                    JetButton(
                        onClick =
                        {
                            val permissionCheckResult =
                                ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                )
                            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                                startCamera()
                            } else {
                                // Request a permission
                                permissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                        },
                        color = BluePrimary,
                        enabled = true,
                        label = "CAMERA",
                        modifier = Modifier.size(width = 200.dp, height = 50.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    JetButton(onClick = { uploadImage(capturedImageUri, context) }, color = BlueText, enabled = true, label = "UPLOAD")
                }

            }
        }
    }

    private fun uploadImage(uri: Uri, context: Context){
        val uploadViewModel = UploadViewModel(context)
        val recipeViewModel = RecipeViewModel(context)
        val imageFile = context.uriToFile(uri, context).reduceFileImage()
        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            imageFile.name,
            requestImageFile
        )
        uploadViewModel.insertIngredient(multipartBody).observe(this) { recipe ->
            Handler().postDelayed({
                when (recipe) {
                    is Result.Success -> {
                        val recipeList = ArrayList<RecipeEntity>()
                        recipeList.addAll(recipe.data)
                        val intent = Intent(context, RecipePage::class.java)
                        intent.putExtra("recipeList", recipeList)
                        context.startActivity(intent)
                    }
                    is Result.Error -> {
                        JetToast(recipe.error, context)
                    }
                    else -> {
                    }
                }
            }, 20000)
        }

//        recipeViewModel.getAllRecipe().observe(this){
//                res ->
//            res.forEach { recipe ->
//                Log.d("test123", recipe.name)
//            }
//        }
//        val intent = Intent(context, RecipePage::class.java)
//        context.startActivity(intent)

    }
}

