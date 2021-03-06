import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../login/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isLoggedIn = false;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.carregarSource();
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
  }

  handleLogout() {
    this.authenticationService.logout();
  }

  getSource() {
    return this.authenticationService.source;
  }

}
