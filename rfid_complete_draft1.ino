#include <Ethernet.h>
#include <SPI.h>
#include <sha1.h>
#include <mysql.h>
#include <stdlib.h>

#define holdPin 7 //blue wire

volatile unsigned long lastBitArrivalTime;
volatile byte tagID[35];
volatile int bitCount = 0;

byte mac_addr[] = {0x90, 0xA2, 0xDA, 0x0E, 0xF5, 0xEE}; //MAC address of the ethernet shield
IPAddress server_addr(127,0,0,1); //IP address of localhost

char user[] = "root";
char password[] = "ece4894project3#";
const char SELECT_QUERY[] = "SELECT ";

Connector my_conn;


void ISR_zero(void) {
  lastBitArrivalTime = millis();
  tagID[bitCount] = 0;
  bitCount++;
}

void ISR_one(void) {
  lastBitArrivalTime = millis();
  tagID[bitCount] = 1;
  bitCount++;
}

void setup() {
  Serial.begin(57600);
  //db_connect();
  pinMode(holdPin, OUTPUT);
  digitalWrite(holdPin, HIGH);
  
  attachInterrupt(0,ISR_zero, FALLING);
  attachInterrupt(1,ISR_one, FALLING);
  
  for(int i =0; i < 35; i++) {
    tagID[i] = 0x00;
  }
  bitCount =0;
}

void loop() {
  if (bitCount > 0 && millis() - lastBitArrivalTime > 250) {
    unsigned long cc_data = parseId();
    Serial.println(cc_data);
    //int signal = execute_query(cc_data);
    bitCount = 0;  
  } 
}


int execute_query(unsigned long cc_data) {
  int signal = false;
  
  
  return signal;
}

unsigned long parseId() {
  unsigned long cc_data = 0;
  for (int i = 14; i < 34; i++) { //For a 35 bit Wiegand, bit 1 to 13 are facility code and 14 to 33 are consumer code)
    cc_data <<= 1;
    cc_data = cc_data | tagID[i];
    tagID[i] = 0x00;
  }
  return cc_data;
}

void db_connect() {
  Ethernet.begin(mac_addr);
  delay(1000);
  Serial.println("Connecting...");
  if (my_conn.mysql_connect(server_addr, 3306, user,password)) {
    delay(1000);
  } else {
    Serial.println("Connection failed");
  }
}


