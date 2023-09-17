import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddContentComponent } from './add-content/add-content.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EditContentComponent } from './edit-content/edit-content.component';
import { LoginComponent } from './login/login.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [

  {
    path: "home",
    component: LoginComponent
  },
  {
    path: "edit-content/:id",
    component: EditContentComponent
  },
  {
    path: "dashBoard",
    component: DashboardComponent
  },
  {
    path: "register",
    component: RegistrationComponent
  },{
    path: "dashBoard/addContent",
    component: AddContentComponent
  },
  {
    path: "",
    redirectTo: "/home",
    pathMatch: "full"
  },
  {
    path:"**",
    component: PagenotfoundComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
