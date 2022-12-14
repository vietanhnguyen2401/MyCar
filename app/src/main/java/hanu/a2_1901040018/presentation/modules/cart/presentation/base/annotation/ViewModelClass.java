package hanu.a2_1901040018.presentation.modules.cart.presentation.base.annotation;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewModelClass {
    Class<? extends ViewModel> value();
}
