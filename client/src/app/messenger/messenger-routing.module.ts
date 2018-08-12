import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MessengerComponent } from './messenger.component';

const routes: Routes = [
    {
        path: '',
        component: MessengerComponent,
    },
    {
        path: ':id',
        component: MessengerComponent,
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MessengerRoutingModule { }
