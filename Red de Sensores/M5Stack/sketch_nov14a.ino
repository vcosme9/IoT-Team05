#include "WiFi.h"
#include "AsyncUDP.h"
#define BLANCO 0XFFFF
#define NEGRO 0
#define ROJO 0xF800
#define VERDE 0x07E0
#define AZUL 0x001F
#include <M5Stack.h>

#include <MFRC522.h>
#include <ArduinoJson.h>
#include <SD.h>


const char * ssid = "Team_05";
const char * password = "Team_05_GTI";

String laID;
char texto[200]="";

//int hora;

boolean rec=0;

int id;


const int RST_PIN = 2;            // Pin 9 para el reset del RC522
const int SS_PIN = 21;            // Pin 21 para el SS (SDA) del RC522
MFRC522 mfrc522(SS_PIN, RST_PIN);   // Crear instancia del MFRC522
 
void printArray(byte *buffer, byte bufferSize) {
   for (byte i = 0; i < bufferSize; i++) {
      //Serial.print(buffer[i] < 0x10 ? " 0" : " ");
     // Serial.print(buffer[i], HEX);
     id =id + buffer[i];
          
   }
   
}
  void setup(){
M5.begin();
M5.Lcd.setTextSize(2); //Tamaño del texto
Serial.begin(9600);
SPI.begin();         //Función que inicializa SPI
mfrc522.PCD_Init();     //Función  que inicializa RFID
WiFi.mode(WIFI_STA);
WiFi.begin(ssid, password);
if (WiFi.waitForConnectResult() != WL_CONNECTED) {
Serial.println("WiFi Failed");
while(1) {
delay(1000);
}
}else {Serial.println("--WIFI OK--");}


}


void loop() {
  //const size_t CAPACITY = JSON_OBJECT_SIZE(1);
  //StaticJsonDocument<CAPACITY> objeto;
  //JsonObject envio = objeto.to<JsonObject>();
   //StaticJsonBuffer<200> jsonBuffer;                 //tamaño maximo de los datos
   
   

  if (mfrc522.PICC_IsNewCardPresent())
   {
      if (mfrc522.PICC_ReadCardSerial())
      {
        id=0;
         
         printArray(mfrc522.uid.uidByte, mfrc522.uid.size);
         laID = String(id);
         //int cuantos = laID.length();
         //laID.toCharArray(texto,200);
         
         
         
        // envio["ID"] = laID;
         //Serial.println(laID);
         
         //serializeJson(laID, Serial);
           
         
         
         
         
   
                 
         
 
         // Finalizar lectura actual
         mfrc522.PICC_HaltA();
      }
   }
    
  
    
  //Envío de datos a la Raspberry
    if(Serial.available() > 0){     
    char command = (char)Serial.read();
    
    
    switch(command){
    case 'H':
      
     //serializeJson(laID, Serial); //envío por el puerto serie el objeto
      Serial.print(laID);
      
      break;
    
    }
    }
}


