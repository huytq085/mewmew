import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessengerComponent } from './messenger.component';
import { SharedModule } from '../shared';
import { MessengerRoutingModule } from './messenger-routing.module'


@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        MessengerRoutingModule
    ],
    declarations: [MessengerComponent]
})
export class MessengerModule { }
