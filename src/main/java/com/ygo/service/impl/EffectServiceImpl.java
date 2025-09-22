package com.ygo.service.impl;

import com.ygo.repository.EffectRepository;
import com.ygo.service.EffectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EffectServiceImpl implements EffectService {

    private final EffectRepository effectRepository;



}



