import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import internal.GlobalVariable

// 3. Mendapatkan hasil dari respons GET
def getRequest = findTestObject('GET/GetBookingIds')
def responseGet = WS.sendRequest(getRequest)

// Menampilkan respons GET jika diperlukan
println("Response Code GET: " + responseGet.getStatusCode())
println("Response Body GET: " + responseGet.getResponseText())

// Pem-parsing JSON menggunakan JsonSlurper
def jsonSlurper = new JsonSlurper()
def jsonArray = jsonSlurper.parseText(responseGet.getResponseText())

// Mengambil satu ID (contoh: mengambil ID pertama)
def firstBookingId = jsonArray[0].bookingid

// Menampilkan ID yang diambil
println("First Booking ID: $firstBookingId")

GlobalVariable.ID = firstBookingId