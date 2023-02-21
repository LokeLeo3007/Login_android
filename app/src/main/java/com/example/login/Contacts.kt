package com.example.login

import java.io.Serializable


class Contacts(var name:String ?= null,var number:String ?= null, var isExpanded: Boolean = false) :
    Serializable {

}