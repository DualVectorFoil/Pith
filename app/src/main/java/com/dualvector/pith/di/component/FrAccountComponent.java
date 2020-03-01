package com.dualvector.pith.di.component;

import com.dualvector.pith.di.module.FrAccountModule;
import com.dualvector.pith.mvp.ui.fragment.AccountFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FrAccountModule.class)
public interface FrAccountComponent {

    void inject(AccountFragment fragment);
}
