package com.mylearning.domain.ports.input.message.listener.payment;

import com.mylearning.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentcompleted(PaymentResponse paymentResponse);
    void paymentCanceled(PaymentResponse paymentResponse);
}
