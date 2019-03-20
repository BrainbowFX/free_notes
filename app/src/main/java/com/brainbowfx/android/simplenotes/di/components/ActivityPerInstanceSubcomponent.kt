package com.brainbowfx.android.simplenotes.di.components

import com.brainbowfx.android.simplenotes.di.modules.*
import com.brainbowfx.android.simplenotes.di.modules.binders.ActivityPerInstanceBindersModule
import com.brainbowfx.android.simplenotes.di.scopes.ActivityPerInstance
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class, ActivityPerInstanceBindersModule::class])
@ActivityPerInstance
interface ActivityPerInstanceSubcomponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): ActivityPerInstanceSubcomponent.Builder
        fun build(): ActivityPerInstanceSubcomponent
    }

}