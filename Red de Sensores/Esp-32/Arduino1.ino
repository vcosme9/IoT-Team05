#include "Presencia.h"
#include "WiFi.h" 
#include "AsyncUDP.h"

//SENSOR DE PRESENCIA
#define PRESENCIA 16
#define LED 22

Presencia sp(PRESENCIA);

//SENSOR DE DISTANCIA
const int EchoPin = 34;
const int TriggerPin = 35;

//CAMBIAR
const char * ssid = "Team_05"; 
const char * password = "Team_05_GTI";

char texto[20];
char textoPresencia[20];
boolean rec=0;
AsyncUDP udp;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  sp.setup();
  pinMode(TriggerPin, OUTPUT);
  pinMode(EchoPin, INPUT);
  
  pinMode(LED, OUTPUT);

  WiFi.mode(WIFI_STA); 
  WiFi.begin(ssid, password); 
  if (WiFi.waitForConnectResult() != WL_CONNECTED) { 
    Serial.println("WiFi Failed"); 
    while(1) { 
      delay(1000); 
      } 
  }   
  if(udp.listen(1234)) { 
    Serial.print("UDP Listening on IP: "); 
    Serial.println(WiFi.localIP()); 
    udp.onPacket([](AsyncUDPPacket packet) {
      int i=20; 
      while (i--) {
        *(texto+i)=*(packet.data()+i);
      } 
      rec=1;        //indica mensaje recibido
    });  
  }
}

void loop() {
  // put your main code here, to run repeatedly:
  if(sp.hayMovimiento()){
    Serial.println("Hay movimiento.");
    sprintf (textoPresencia, "Se mueve" );
     udp.broadcastTo(textoPresencia,3030);
    digitalWrite(LED, HIGH);
  }else{
    Serial.println("No hay movimiento.");
    sprintf (textoPresencia, "No se mueve" ); 
    udp.broadcastTo(textoPresencia,3030);
    digitalWrite(LED, LOW);
  }
  Serial.print("Distancia: ");
  int dis = distancia(TriggerPin, EchoPin);
  
  Serial.println(dis);
  char cstr[16];
  String str = String(dis);
  str.toCharArray(cstr,16);
  Serial.println(cstr);
  sprintf (texto, cstr);
  udp.broadcastTo(texto,3030);
  delay(1000);
}


  int distancia(int TriggerPin, int EchoPin) {
  long duracion, distanciaCm;
  digitalWrite(TriggerPin, LOW); //nos aseguramos señal baja al principio
  delayMicroseconds(4);
  digitalWrite(TriggerPin, HIGH); //generamos pulso de 10us
  delayMicroseconds(10);
  digitalWrite(TriggerPin, LOW);
  duracion = pulseIn(EchoPin, HIGH); //medimos el tiempo del pulso
  distanciaCm = duracion * 10 / 292 / 2; //convertimos a distancia
  return distanciaCm;
  }
