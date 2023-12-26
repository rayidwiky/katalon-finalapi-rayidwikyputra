import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper
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



