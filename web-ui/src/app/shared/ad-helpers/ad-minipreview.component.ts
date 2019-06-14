import { Component, Input } from '@angular/core';

import { Ad } from 'src/app/core';

import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-ad-minipreview',
  templateUrl: './ad-minipreview.component.html',
  styleUrls: ['./ad-minipreview.component.scss']
})
export class AdMiniPreviewComponent {

  @Input() ad: Ad;
  @Input() thumbnailUrl: string;

  imagesUrl = `${environment.api_url}/image/`;

}
