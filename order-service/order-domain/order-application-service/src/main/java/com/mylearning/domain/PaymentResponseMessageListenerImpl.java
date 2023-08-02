package com.mylearning.domain;

import com.mylearning.domain.dto.message.PaymentResponse;
import com.mylearning.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {
    @Override
    public void paymentcompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCanceled(PaymentResponse paymentResponse) {

    }
}
