package com.nhatle.learnrecyclerview

import com.fasterxml.jackson.annotation.JsonProperty

//"id": "B27DCCN100",
//"full_name": {
//    "first": "Hoa",
//    "last": "Mai",
//    "mid": "Thị"
//},
//"gender": "Nữ",
//"birth_date": "10/10/2009",
//"email": "hoamai@xmail.com",
//"address": "Nghệ An",
//"major": "CNTT",
//"gpa": 3.25,
//"year": 2
data class Student(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("full_name")
    val fullName: FullName,
    @JsonProperty("gender")
    val gender: String,
    @JsonProperty("birth_date")
    val birthDate: String,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("address")
    val address: String,
    @JsonProperty("major")
    val major: String,
    @JsonProperty("gpa")
    val gpa: Double,
    @JsonProperty("year")
    val year: Int
)
data class FullName(
    @JsonProperty("first")
    val first: String,
    @JsonProperty("last")
    val last: String,
    @JsonProperty("mid")
    val mid: String
){
    override fun toString(): String {
        return "$first $mid $last"
    }

}