package com.lt.constants;

public enum ModeOfPayment {
    CREDIT_CARD,NET_BANKING,DEBIT_CARD,CASH;

    public static ModeOfPayment getModeofPayment(int value)
    {
        switch(value)
        {
            case 1:
                return ModeOfPayment.CASH;
            case 2:
                return ModeOfPayment.CREDIT_CARD;
            case 3:
                return ModeOfPayment.DEBIT_CARD;
            default:
                return null;
        }

    }
}
