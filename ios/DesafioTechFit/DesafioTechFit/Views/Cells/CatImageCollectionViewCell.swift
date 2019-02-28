//
//  CatImageCollectionViewCell.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 22/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage


class CatImageCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var imgCat: UIImageView! 
    
    func prepare(with catImage: CatImage) {
        self.imgCat.loadImageUsingUrlString(urlString: catImage.url)
    }
    
    
}
