package app.grapheneos.setupwizard.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import app.grapheneos.setupwizard.action.SetupWizard
import app.grapheneos.setupwizard.action.UpdaterSecurityPreviewActions

class UpdaterSecurityPreviewActivity : ProxyActivity() {

    companion object {
        private const val TAG = "UpdaterSecPrevActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!UpdaterSecurityPreviewActions.isAvailable(this)) {
            Log.d(TAG, "onCreate: skipping: missing updater")
            finish()
            SetupWizard.next(this)
        }
    }

    override fun launchActual() {
        UpdaterSecurityPreviewActions.launchSetup(this)
    }

    override fun handleResult(resultCode: Int, data: Intent?) {
        Log.d(TAG, "handleResult: $resultCode")
        if (resultCode != Activity.RESULT_CANCELED) {
            setMovingForward()
        } else {
            finish()
        }
        UpdaterSecurityPreviewActions.handleResult(this, resultCode)
    }
}
