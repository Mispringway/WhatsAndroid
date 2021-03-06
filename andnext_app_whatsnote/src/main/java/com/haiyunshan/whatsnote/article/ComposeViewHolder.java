package com.haiyunshan.whatsnote.article;

import android.app.Activity;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import club.andnext.recyclerview.bridge.BridgeViewHolder;
import com.haiyunshan.whatsnote.article.entity.DocumentEntity;

public abstract class ComposeViewHolder<E extends DocumentEntity> extends BridgeViewHolder<E> {

    protected E entity;

    Callback callback;

    public ComposeViewHolder(Callback callback, View itemView) {
        super(itemView);

        this.callback = callback;
    }

    @Override
    public abstract int getLayoutResourceId();

    @Override
    public abstract void onViewCreated(@NonNull View view);

    @Override
    @CallSuper
    public void onBind(E item, int position) {
        this.entity = item;
    }

    @Override
    @CallSuper
    public void onViewAttachedToWindow() {

    }

    @Override
    @CallSuper
    public void onViewDetachedFromWindow() {
        save();
    }

    void remove() {
        callback.remove(this);
    }

    abstract void save();

    E getEntity() {
        return entity;
    }

    /**
     *
     */
    public static abstract class Callback {

        boolean enable;

        Activity context;

        public Callback(Activity context) {
            this.context = context;

            this.enable = true;
        }

        public Activity getContext() {
            return this.context;
        }

        public boolean isEnable() {
            return this.enable;
        }

        public int getMaxWidth() {
            return getContext().getResources().getDisplayMetrics().widthPixels;
        }

        public int getMaxHeight() {
            return getContext().getResources().getDisplayMetrics().heightPixels;
        }

        public abstract void remove(ComposeViewHolder viewHolder);
    }
}
