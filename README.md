# Android Permissions

# Proyecto donde se muestra como utilizar los permisos en android

Libreria que facilita solicitar los permisos

compile 'com.kishan.askpermission:askpermission:1.0.3'

##Ejemplo de uso:

    private void askPermissionsWithLibrary(){
        new AskPermission.Builder(this)
                .setPermissions(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION)
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
                .request(10);
    }