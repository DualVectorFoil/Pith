package com.dualvector.pith.di.component;

import com.dualvector.pith.di.module.FrRegisterModule;
import com.dualvector.pith.mvp.ui.fragment.RegisterFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FrRegisterModule.class)
public interface FrRegisterComponent {

    void inject(RegisterFragment fragment);
}
