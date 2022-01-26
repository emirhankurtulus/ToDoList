package com.emirhan.topnote.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.emirhan.topnote.databinding.ActivityUploadBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class upload extends AppCompatActivity {
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    Bitmap selectedImage;

    Uri imageData;

    private ActivityUploadBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        registerLauncher();
firebaseStorage = FirebaseStorage.getInstance();
auth = FirebaseAuth.getInstance();
firebaseFirestore=FirebaseFirestore.getInstance();
storageReference = firebaseStorage.getReference();

    }
public void profile(View View)
{
    String subject = binding.subject.getText().toString();
    String words = binding.words.getText().toString();
    if(words.equals("") && subject.equals("")) {
        Intent otherpage = new Intent(upload.this, profile.class);
        startActivity(otherpage);
    }
    else
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Do you want to discard this?");
        alert.setMessage("they will be deleted");
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent otherpage = new Intent(upload.this, profile.class);
                startActivity(otherpage);
                finish();
            }
        });
        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();

    }
}
    public void home(View View)
    {

        String subject = binding.subject.getText().toString();
        String words = binding.words.getText().toString();
        if(words.equals("") && subject.equals("")) {
            Intent otherpage = new Intent(upload.this, firstpage.class);
            startActivity(otherpage);
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Do you want to discard this");
            alert.setMessage("they will delete");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent otherpage = new Intent(upload.this, firstpage.class);
                    startActivity(otherpage);
                    finish();
                }
            });
            alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();

        }
    }

    public void done(View View) {
        String subject = binding.subject.getText().toString();
        String words = binding.words.getText().toString();
        if (words.equals("") && subject.equals("")) {
            Toast.makeText(upload.this, "Please write Somethings", Toast.LENGTH_LONG).show();
        } else {
            if (imageData == null) {
                Toast.makeText(upload.this, "Please upload a photo", Toast.LENGTH_LONG).show();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Do you want to save this?");
                alert.setMessage("You can edit or delete always");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (imageData != null) {
                            UUID uuid = UUID.randomUUID();
                            String imageName = "images/" + uuid + ".jpg";
                            storageReference.child(imageName).putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    StorageReference newReferance = firebaseStorage.getReference(imageName);
                                    newReferance.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String downloadUrl = uri.toString();

                                            FirebaseUser user = auth.getCurrentUser();
                                            String email = user.getEmail();
                                            HashMap<String, Object> postData = new HashMap<>();
                                            postData.put("useremail", email);
                                            postData.put("words", words);
                                            postData.put("subject", subject);
                                            postData.put("downloadurl", downloadUrl);
                                            postData.put("date", FieldValue.serverTimestamp());


                                            firebaseFirestore.collection("notes").add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    Intent otherpage = new Intent(upload.this, firstpage.class);
                                                    startActivity(otherpage);
                                                    finish();
                                                    Toast.makeText(upload.this, "Your notes has shared successfully", Toast.LENGTH_LONG).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(upload.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        }
    }
    public void button(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(view,"Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                    }
                }).show();
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        } else {
            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGallery);

        }

    }

    public void registerLauncher() {
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intentFromResult = result.getData();
                            if (intentFromResult != null) {
                                imageData = intentFromResult.getData();
                                try {

                                    if (Build.VERSION.SDK_INT >= 28) {
                                        ImageDecoder.Source source = ImageDecoder.createSource(upload.this.getContentResolver(),imageData);
                                        selectedImage = ImageDecoder.decodeBitmap(source);
                                        binding.imageView7.setImageBitmap(selectedImage);
                                        binding.imageView7.setVisibility(View.VISIBLE);

                                    } else {
                                        selectedImage = MediaStore.Images.Media.getBitmap(upload.this.getContentResolver(),imageData);
                                        binding.imageView7.setImageBitmap(selectedImage);
                                        binding.imageView7.setVisibility(View.VISIBLE);
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
                });


        permissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result) {
                            //permission granted
                            Intent intentToGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            activityResultLauncher.launch(intentToGallery);

                        } else {
                            //permission denied
                            Toast.makeText(upload.this,"Permisson needed!",Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

}
