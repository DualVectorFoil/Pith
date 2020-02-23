package com.dualvector.pith.di.component;

import com.dualvector.pith.di.module.LoginModule;
import com.dualvector.pith.mvp.ui.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}
