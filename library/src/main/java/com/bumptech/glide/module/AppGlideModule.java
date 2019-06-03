package com.bumptech.glide.module;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;

import androidx.annotation.NonNull;

/**
 * Defines a set of dependencies and options to use when initializing Glide within an application.
 * <p>定义在应用中初始化 Glide 时要使用的一组 dependencies 和 options
 *
 * <p>There can be at most one {@link AppGlideModule} in an application. Only Applications can
 * include a {@link AppGlideModule}. Libraries must use {@link LibraryGlideModule}.
 * <p>应用程序中最多只能有一个 {@link AppGlideModule}。只有 Applications 可以包含 {@link AppGlideModule}。
 * Libraries 必须使用 {@link LibraryGlideModule}。
 *
 * <p>Classes that extend {@link AppGlideModule} must be annotated with {@link
 * com.bumptech.glide.annotation.GlideModule} to be processed correctly.
 * <p>继承 {@link AppGlideModule} 的类必须使用 {@link GlideModule} 注解才能被正确的处理。
 *
 * <p>Classes that extend {@link AppGlideModule} can optionally be annotated with {@link
 * com.bumptech.glide.annotation.Excludes} to optionally exclude one or more {@link
 * LibraryGlideModule} and/or {@link GlideModule} classes.
 * <p>继承 {@link AppGlideModule} 的类可以选择使用{@link com.bumptech.glide.annotation.Excludes}
 * 进行注释，以选择性地排除一个或多个 {@link LibraryGlideModule} and/or {@link GlideModule} 类。
 *
 * <p>Once an application has migrated itself and all libraries it depends on to use Glide's
 * annotation processor, {@link AppGlideModule} implementations should override {@link
 * #isManifestParsingEnabled()} and return {@code false}.
 * <p>一旦应用程序迁移了自己以及它依赖的所有库使用Glide的注释处理器，
 * {@link AppGlideModule} 的实现应该重写 {@link #isManifestParsingEnabled()} 并返回 {@code false}。
 */
// Used only in javadoc.
@SuppressWarnings("deprecation")
public abstract class AppGlideModule extends LibraryGlideModule implements AppliesOptions {
  /**
   * Returns {@code true} if Glide should check the AndroidManifest for {@link GlideModule}s.
   * <p>如果 Glide 应检查 AndroidManifest 的 {@link GlideModule}，则返回{@code true}。
   *
   * <p>Implementations should return {@code false} after they and their dependencies have migrated
   * to Glide's annotation processor.
   *
   * <p>Returns {@code true} by default.
   */
  public boolean isManifestParsingEnabled() {
    return true;
  }

  @Override
  public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    // Default empty impl.
  }
}
