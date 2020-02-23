package com.dualvector.pith.di.component;

import com.dualvector.pith.di.module.FrLoginModule;
import com.dualvector.pith.mvp.ui.fragment.LoginFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FrLoginModule.class)
public interface FrLoginComponent {

    void inject(LoginFragment fragment);
}
