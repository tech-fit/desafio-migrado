//
//  ImageViewExtension.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 22/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import Foundation
import UIKit
import Alamofire
import AlamofireImage
import SwiftGifOrigin

class CustomImageView: UIImageView {
    
    override var image: UIImage? {
        didSet {
            layer.borderWidth = CGFloat(2.0)
            //let borderColor: UIColor = .white
            layer.borderColor = UIColor(named: "darkBackground")!.cgColor
            
            let cornerRadius: CGFloat = 5
            layer.cornerRadius = cornerRadius
        }
    }
    
}


let imageCache = NSCache<AnyObject, AnyObject>()

extension UIImageView {
    
    func dropShadow(scale: Bool = true) {
        //layer.masksToBounds = false
        layer.shadowColor = UIColor.black.cgColor
        layer.shadowOpacity = 0.5
        layer.shadowOffset = CGSize(width: -1, height: 1)
        layer.shadowRadius = 1
        
        layer.shadowPath = UIBezierPath(rect: bounds).cgPath
        layer.shouldRasterize = true
        layer.rasterizationScale = scale ? UIScreen.main.scale : 1
    }
    
    func loadImageUsingUrlString(urlString: String) {
        self.image = UIImage.gif(name: "loading") 
        
        if let imageFromCache = imageCache.object(forKey: urlString as AnyObject) as? UIImage {
            self.image = imageFromCache
            return
        }
        
        Alamofire.request(urlString).responseImage { response in
            if let image = response.result.value {
                let imageToCache = image
                imageCache.setObject(imageToCache, forKey: urlString as AnyObject)
                self.image = imageToCache
            }
        }
    }
    
}
