package hr.nullteam.rsc.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import hr.nullteam.rsc.R;

public final class IntentUtils {

    private static final String INTENT_TYPE_IMAGE = "image/*";

    private static final String URL_PREFIX = "http";
    private static final String HTTP_PREFIX = "http://";
    private static final String WEB_PREFIX = "www";
    private static final String TWITTER_HANDLE_PREFIX = "@";
    private static final String TWITTER_PREFIX = "twitter";
    private static final String TWITTER_URL = "twitter://user?screen_name=";
    private static final String URL_DELIMITER = "/";

    private final Context context;

    public IntentUtils(Context context) {
        this.context = context;
    }

    public Intent createEmailClientIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Email here"});    // TODO
        return Intent.createChooser(intent, context.getString(R.string.chooser_email_dialog_title));
    }

    public Intent createCameraIntent(final Uri outputUri) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        return cameraIntent;
    }

    public Intent createOpenPhotoGalleryIntent() {
        Intent intent = new Intent();
        intent.setType(INTENT_TYPE_IMAGE);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        return Intent.createChooser(intent, context.getString(R.string.select_picture));
    }

    public Intent createMapIntent(Uri uri) {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(uri);
        return Intent.createChooser(mapIntent, context.getString(R.string.select_picture));
    }

    public Intent createEmailClientIntent(String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        return Intent.createChooser(intent, context.getString(R.string.chooser_email_dialog_title));
    }

    public Intent createPhoneIntent(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        return Intent.createChooser(intent, context.getString(R.string.chooser_call_dialog_title));
    }

    public Intent getWebIntent(String url) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(checkForPrefix(url)));
        return webIntent;
    }

    public Intent createTwitterIntent(String twitterHandle) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(TWITTER_URL + stripTwitterUsername(twitterHandle)));
        return Intent.createChooser(intent, null);
    }

    private String checkForPrefix(String url) {
        if (url.startsWith(URL_PREFIX)) {
            return url;
        }
        return HTTP_PREFIX + url;
    }

    private String stripTwitterUsername(String twitterHandle) {
        String username = "";
        if (twitterHandle.startsWith(URL_PREFIX) || twitterHandle.startsWith(TWITTER_PREFIX) || twitterHandle.startsWith(WEB_PREFIX)) {
            String[] splitData = twitterHandle.split(URL_DELIMITER);
            int index = splitData.length - 1;
            while (TextUtils.isEmpty(username)) {
                username = splitData[index];
                index--;
            }
        } else if (twitterHandle.startsWith(TWITTER_HANDLE_PREFIX)) {
            username = twitterHandle.substring(1);
        } else {
            username = twitterHandle;
        }
        return username;
    }

}
