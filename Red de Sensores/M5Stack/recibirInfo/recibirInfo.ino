#include "WiFi.h"
#include "AsyncUDP.h"
#define BLANCO 0XFFFF
#define NEGRO 0
#define ROJO 0xF800
#define VERDE 0x07E0
#define AZUL 0x001F
#include <M5Stack.h>
#include <MQTT.h>

const char * ssid = "Team_05";
const char * password = "Team_05_GTI";

char texto[20];

//int hora;

boolean rec = 0;

//--MQTT
const char broker[] = "iot.eclipse.org";
WiFiClient net;
MQTTClient client;

unsigned long lastMillis = 0;

void connect() {
  Serial.print("checking wifi...");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }

  Serial.print("\nconnecting...");
  while (!client.connect("ardu", "try", "try")) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("\nconnected!");
  client.subscribe("grupo7/practica/#");
 //client.unsubscribe("<usuario>/practica/#");
}

void messageReceived(String &topic, String &payload) {
  Serial.println("incoming: " + topic + " - " + payload);
}
//---MQTT

//--Sensor de ID
int id;
const int RST_PIN = 2;            // Pin 9 para el reset del RC522
const int SS_PIN = 21;            // Pin 21 para el SS (SDA) del RC522
//MFRC522 mfrc522(SS_PIN, RST_PIN);   // Crear instancia del MFRC522

void printArray(byte *buffer, byte bufferSize) {
  for (byte i = 0; i < bufferSize; i++) {
    //Serial.print(buffer[i] < 0x10 ? " 0" : " ");
    // Serial.print(buffer[i], HEX);
    id = id + buffer[i];

  }
//---Sensor de ID

  AsyncUDP udp;

  void setup() {
    M5.begin();
    M5.Lcd.setTextSize(2); //Tamaño del texto
    Serial.begin(9600);
    WiFi.mode(WIFI_STA);
    WiFi.begin(ssid, password);
    if (WiFi.waitForConnectResult() != WL_CONNECTED) {
      Serial.println("WiFi Failed");
      while (1) {
        delay(1000);
      }
    }

   client.begin(broker, net);
  client.onMessage(messageReceived);
  connect();
    if (udp.listen(3030)) {
      Serial.print("UDP Listening on IP: ");
      Serial.println(WiFi.localIP());
      udp.onPacket([](AsyncUDPPacket packet) {
        int i = 20;
        while (i--) {
          *(texto + i) = *(packet.data() + i);
        }
        rec = 1; //indica mensaje recibido
      });
    }
  }

  void loop() {
  client.loop();
      delay(10);
    if (rec) {
      //Send broadcast
      rec = 0; //mensaje procesado
      udp.broadcastTo("Recibido", 3030/*Cambiar al puerto que quiera*/); //envia confirmacion
      udp.broadcastTo(texto, 3030); //y dato recibido
      hora=atol(texto); //paso de texto a entero
      Serial.println (texto);
      //Serial.println (hora);

     String distancia=texto["Altura"];
      
      //mandar a M%Stack
      M5.Lcd.fillScreen(NEGRO); //borrar pantalla
      M5.Lcd.setCursor(0, 10); //posicion inicial del cursor
      M5.Lcd.setTextColor(BLANCO); //color del texto
      M5.Lcd.print(texto);

      //--MQTT
      if (!client.connected()) {
    connect();
  }
   client.publish("neverapp/grupo5/holamundo","Hola Mundo");

      //--Sensor de ID
      if (mfrc522.PICC_IsNewCardPresent())
      {
        if (mfrc522.PICC_ReadCardSerial())
        {
          id = 0;
          printArray(mfrc522.uid.uidByte, mfrc522.uid.size);
          laID = String(id);

          // Finalizar lectura actual
          mfrc522.PICC_HaltA();
        }
      }
      //---Sensor de ID



      //--Envío de datos a la Raspberry
      if (Serial.available() > 0) {
        char command = (char)Serial.read();
        switch (command) {
          case 'H':
            Serial.print(laID);
            break;
          case 'D':        
            //recibo["Altura"].printTo(Serial);
            //Serial.println(distancia);
            break;
        }
      }
      //---Envío de datos a la Raspberry

      delay(3000);
    }
  }
