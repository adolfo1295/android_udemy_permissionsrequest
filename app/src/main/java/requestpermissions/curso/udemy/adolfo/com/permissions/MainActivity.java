package requestpermissions.curso.udemy.adolfo.com.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.kishan.askpermission.AskPermission;
import com.kishan.askpermission.ErrorCallback;
import com.kishan.askpermission.PermissionCallback;
import com.kishan.askpermission.PermissionInterface;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askPermissionsWithLibrary();
        //askPermissionsNormally();
        //checkMorePermissions();
    }



    private void askPermissionsNormally(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                }
            }
        }
    }

    private void checkMorePermissions(){

        // otra manera para solicitar los permisos es de esta forma
        //donde con ContextCompat.checlSelfPermissions te pdie estos parametros
        //(context, permiso)
        //cabe destacar que se puede colcoar mas de un permiso, como en esta¿e caso, colocamos 2, camara y contactos
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //al verificar que no estan otorgados los permisos se crea un requestPermission
            //que es el que lanzara los dialogs para que confirmes los permisos, o se deniegen
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.CAMERA},1);
        }

    }

    //Metodo que pedira los permisos con una libreria externa
    //Libreria usada - https://github.com/Kishanjvaghela/Ask-Permission
    private void askPermissionsWithLibrary(){
        //se crea un askPermission.Builder, dandole de parametro el contexto de la app
        new AskPermission.Builder(this)
                //aqui van los permisos que se quieren solciitar
                //cabe destacar que deben agregarse primero en manifest.xml
                .setPermissions(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION)
                //aqui se crea el callback para revisar el resultado de los permisos
                //si fueron otorgados o denegados
                .setCallback(new PermissionCallback() {
                    @Override
                    public void onPermissionsGranted(int requestCode) {
                        Toast.makeText(MainActivity.this, "yeei permisoss otorgados", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionsDenied(int requestCode) {
                        Toast.makeText(MainActivity.this, "che culo", Toast.LENGTH_SHORT).show();
                    }
                })
                //callback en caso de algun error
                .setErrorCallback(new ErrorCallback() {
                    @Override
                    public void onShowRationalDialog(PermissionInterface permissionInterface, int requestCode) {
                        permissionInterface.onDialogShown();
                    }

                    @Override
                    public void onShowSettings(PermissionInterface permissionInterface, int requestCode) {
                        permissionInterface.onSettingsShown();
                    }
                })
                //se da un requestCode, que este sera la base de los callbacks
                .request(10);
    }

}
