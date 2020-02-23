package com.dualvector.pith.di.component;

import com.dualvector.pith.di.module.FrForgetModule;
import com.dualvector.pith.mvp.ui.fragment.ForgetFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FrForgetModule.class)
public interface FrForgetComponent {

    void inject(ForgetFragment fragment);
}
