# Android Permissions

# Proyecto donde se muestra como utilizar los permisos en android

Libreria que facilita solicitar los permisos

compile 'com.kishan.askpermission:askpermission:1.0.3'

Ejemplo de uso:

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