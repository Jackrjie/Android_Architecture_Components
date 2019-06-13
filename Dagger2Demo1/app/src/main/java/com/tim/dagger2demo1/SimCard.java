package com.tim.dagger2demo1;

import javax.inject.Inject;

/**
 * create by Tim on 6/14/2019
 * email: jackrjie@gmail.com
 */
public class SimCard {
    private ServiceProvider mServiceProvider;

    @Inject
    public SimCard(ServiceProvider serviceProvider) {
        mServiceProvider = serviceProvider;
    }
}
