package by.lugovskoi.service;

import by.lugovskoi.entity.Number;
import by.lugovskoi.entity.Request;

public class BookingServiceImpl implements BookingService {

    @Override
    public boolean book(Request bookingRequest) {
        //some business logic
        return false;
    }

    private boolean checkRequest(Request request){
        if(request.getNumber()!=null && !request.getNumber().isBooked()){
            request.getNumber().setBooked(true);
            return true;
        } else return false;
    }

    private void roomToFree(Number number) {
        number.setBooked(false);
    }

}
