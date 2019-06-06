import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdComponent } from './ad.component';
import { AdResolver } from './ad-resolver.service';

const routes: Routes = [
    {
        path: ':id',
        component: AdComponent,
        resolve: {
            ad: AdResolver
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdRoutingModule { }