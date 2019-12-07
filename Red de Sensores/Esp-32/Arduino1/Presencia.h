class Presencia {
  
  private:
    int pin;
    bool mov;
    
  public:
    
    Presencia(const int _pin){
      (*this).pin= _pin;
    }
    void setup(){
      pinMode((*this).pin, INPUT);
    }
   
    bool hayMovimiento(){
      mov = digitalRead((*this).pin);
      if(mov == HIGH){
        return true;
      }else{
        return false;
      }
    }
 
  
  
}; // class SensorTemperatura
