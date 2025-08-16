import android.content.Context
import android.net.Uri
import androidx.annotation.IdRes
import androidx.navigation.NavController
import java.io.File
import java.io.FileOutputStream
import android.app.DatePickerDialog
import android.view.View
import java.util.Calendar

fun NavController.safeNav(
    @IdRes currentDestId: Int,
    @IdRes actionId: Int
) {
    if (currentDestination?.id == currentDestId) {
        navigate(actionId)
    }
}

fun saveUriToCache(context: Context, uri: Uri): File? {
    return try {
        val fileName = "${System.currentTimeMillis()}.jpg" // or get from uri
        val cacheFile = File(context.cacheDir, fileName)

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(cacheFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        cacheFile
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun View?.showDatePicker(onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    this?.context?.let {
        DatePickerDialog(
            it,
            { _, year, month, dayOfMonth ->
                val dateString = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(dateString)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }?.show()
}
