import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { MatIconModule,MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';
import { CommonLayoutComponent } from './common-layout/common-layout.component';
import { XcomponentsModule } from '../xcomponents/xcomponents.module';
import { SearchComponent } from './search/search.component';
import { FollowListComponent } from './follow-list/follow-list.component';
import { FooterComponent } from './footer/footer.component';
import { FollowUserComponent } from './follow-list/follow-user/follow-user.component';

const X_ICON = `
<svg
  viewBox="0 0 24 24"
  aria-hidden="true"
  class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1nao33i r-rxcuwo r-1777fci r-m327ed r-494qqr"
>
  <g>
    <path
      d="M18.244 2.25h3.308l-7.227 8.26 8.502 11.24H16.17l-5.214-6.817L4.99 21.75H1.68l7.73-8.835L1.254 2.25H8.08l4.713 6.231zm-1.161 17.52h1.833L7.084 4.126H5.117z"
    ></path>
  </g>
</svg>
`;

@NgModule({
  declarations: [SidebarComponent,CommonLayoutComponent, SearchComponent, FollowListComponent, FooterComponent, FollowUserComponent],
  imports: [CommonModule, MatIconModule,XcomponentsModule],
  exports: [SidebarComponent,CommonLayoutComponent],
})
export class LayoutsModule {
  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIconLiteral(
      'x',
      sanitizer.bypassSecurityTrustHtml(X_ICON)
    );
  }
}
