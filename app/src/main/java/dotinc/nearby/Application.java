package dotinc.nearby;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ddvlslyr on 28/2/16.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        printHashKey();
    }

    void printHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "dotinc.nearby",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
