package com.bumptech.glide.module;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.Excludes;

import androidx.annotation.NonNull;

/**
 * Registers a set of components to use when initializing Glide within an app when Glide's
 * annotation processor is used.
 * <p>使用注解处理器在应用中初始化 Glide 时，注册一组组件
 *
 * <p>Any number of LibraryGlideModules can be contained within any library or application.
 * <p>任何库或应用程序中都可以包含任意数量的LibraryGlideModules
 *
 * <p>LibraryGlideModules are called in no defined order. If LibraryGlideModules within an
 * application conflict, {@link AppGlideModule}s can use the {@link Excludes} annotation to selectively remove one or more of the
 * conflicting modules.
 * <p>LibraryGlideModules 的调用没有特定的顺序。 如果应用程序中的 LibraryGlideModules 发生冲突，
 * {@link AppGlideModule} 可以使用 {@link Excludes} 注解来有选择地删除一个或多个冲突模块
 */
@SuppressWarnings("deprecation")
public abstract class LibraryGlideModule implements RegistersComponents {
  @Override
  public void registerComponents(
      @NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    // Default empty impl.
  }
}
