package app.grapheneos.setupwizard.action

import android.app.Activity
import android.content.Intent
import app.grapheneos.setupwizard.view.activity.SetupWizardActivity

object UpdaterSecurityPreviewActions {
    private const val TARGET_UPDATER = "app.seamlessupdate.client"
    private const val ACTION_OPEN_SECURITY_PREVIEW_SETTINGS =
        "app.seamlessupdate.client.SECURITY_PREVIEW_SETTINGS"

    private fun getIntent() = Intent(ACTION_OPEN_SECURITY_PREVIEW_SETTINGS).apply {
        setPackage(TARGET_UPDATER)
        addCategory(Intent.CATEGORY_DEFAULT)
    }

    fun isAvailable(context: SetupWizardActivity): Boolean {
        val resolveInfo = getIntent().resolveActivity(context.packageManager)
        return resolveInfo != null;
    }

    fun launchSetup(context: SetupWizardActivity) {
        SetupWizard.startActivityForResult(context, getIntent() )
    }

    fun handleResult(context: Activity, resultCode: Int) {
        if (resultCode != Activity.RESULT_CANCELED) {
            SetupWizard.next(context)
        }
    }
}
