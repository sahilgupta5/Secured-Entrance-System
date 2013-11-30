#define holdPin 7

volatile unsigned long lastBitArrivalTime;
volatile byte tagID[35];
volatile int bitCount = 0;

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
  pinMode(holdPin, OUTPUT);
  digitalWrite(holdPin, HIGH);
  
  attachInterrupt(0,ISR_zero, FALLING);
  attachInterrupt(1,ISR_one, FALLING);
  delay(10);
  
  for(int i =0; i < 35; i++) {
    tagID[i] = 0x00;
  }
  bitCount =0;
}

void loop() {
  if (bitCount > 0 && millis() - lastBitArrivalTime > 250) {
    unsigned long cc_data = parseId();
    Serial.println(cc_data);
    bitCount = 0;  
  } 
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
