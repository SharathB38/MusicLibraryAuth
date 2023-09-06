package com.example.routes

import com.example.utils.appconstant.APIEndPoints.ADD_NEW_SONG
import com.example.utils.appconstant.APIEndPoints.ADMIN_LOGIN
import com.example.utils.appconstant.APIEndPoints.ADMIN_ROUTES
import com.example.utils.appconstant.APIEndPoints.REMOVE_SONG
import com.example.model.AdminLogin
import com.example.model.DeleteSong
import com.example.model.InputSong
import com.example.service.AdminServices
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.adminFunctions(){
    val adminServices: AdminServices by inject()
    route(ADMIN_ROUTES){

        post(ADMIN_LOGIN) {
            val input=call.receive<AdminLogin>()
            adminServices.adminLoginService(input.name!!,input.password!!)
                .apply { call.respond(HttpStatusCode.Created, this)}

        }
        authenticate("Admin") {
            post(ADD_NEW_SONG) {
                val input = call.receive<InputSong>()
                adminServices.songAddService(input)
                    .apply { call.respond(HttpStatusCode.Accepted, this)}
            }
            delete(REMOVE_SONG) {
                val input = call.receive<DeleteSong>()
                adminServices.deleteSongService(input)
                    .apply { call.respond(HttpStatusCode.Accepted, this)}
            }
        }
    }

}

