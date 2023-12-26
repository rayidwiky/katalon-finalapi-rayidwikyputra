import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject

// 1. Mendapatkan token dengan permintaan POST ke CreateToken
def response = WS.sendRequest(findTestObject('POST/CreateToken'))
def responseBody = response.getResponseBodyContent()
// Pem-parsing JSON menggunakan JsonSlurper
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(responseBody)

// Mengambil nilai token
def tokenValue = jsonResponse.token

// Menampilkan nilai token menggunakan WS.comment
WS.comment("Token Value: $tokenValue")

// 2. Membuat objek permintaan DELETE
def deleteRequest = new RequestObject()
deleteRequest.setRestRequestMethod("DELETE")

// Mengambil URL dari Object Repository
def endpointUrl = findTestObject('Object Repository/Delete/DeleteBooking').getRestUrl()
deleteRequest.setRestUrl(endpointUrl)

// Mengatur header properties
def headerProperties = [
    ['Content-Type': 'application/json'],
    ['Accept': 'application/json'],
    // Menambahkan properti Cookie dengan menggunakan token yang didapatkan
    ['Cookie': "token=$tokenValue"]
]
deleteRequest.setHttpHeaderProperties(headerProperties)

// Melakukan permintaan DELETE
def deleteResponse = WS.sendRequest(deleteRequest)

// Menampilkan respons DELETE jika diperlukan
println("Response Code: " + deleteResponse.getStatusCode())
println("Response Body: " + deleteResponse.getResponseText())
