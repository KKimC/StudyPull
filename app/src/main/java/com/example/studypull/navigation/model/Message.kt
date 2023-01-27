package com.example.studypull.navigation.model

data class Message(
    //대화 내용
    var message: String?,
    //접속자 uId
    var sendId : String?
){
    constructor():this("","")
}
