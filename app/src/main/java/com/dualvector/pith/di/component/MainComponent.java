package com.dualvector.pith.di.component;

import com.dualvector.pith.di.module.MainModule;
import com.dualvector.pith.mvp.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity activity);
}
