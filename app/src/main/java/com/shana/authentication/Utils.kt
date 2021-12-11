package com.shana.authentication

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.shana.authentication.base.BaseViewModel
import com.shana.authentication.data.remoteDataSource.Resource
import com.shana.authentication.ui.logIn.LogInFragment
import com.shana.authentication.ui.logIn.LogInViewModel


fun isUserNameValid(user: String?): Boolean {
    if (user != null)
        return user.length > 1
    else
        return false
}

fun isPassWordValid(password: String?): Boolean {
    if (password != null)
        return password.length > 1
    else
        return false
}

fun View.snackbar(massage:String, action:(()->Unit)?=null){
    val snackBar = Snackbar.make(this,massage,Snackbar.LENGTH_LONG)
    action?.let {
        snackBar.setAction("Retry"){
            it()
        }
    }
    snackBar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    viewModel: BaseViewModel,
    retry:(()->Unit)?=null
){
    when{
        failure.isNetWorkError ->requireView().snackbar("please check your internet connection",retry)
        failure.errorCode==401->{
            if(this is LogInFragment){
                requireView().snackbar("you have entered incorrect email or password")
            }else{
                viewModel.logOut()
            }
        }
        else ->{
            val error = failure.errorResponseBody?.string().toString()
            requireView().snackbar(error)
        }
    }
}