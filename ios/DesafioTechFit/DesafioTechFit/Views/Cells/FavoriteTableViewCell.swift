//
//  FavoriteTableViewCell.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 27/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import UIKit

class FavoriteTableViewCell: UITableViewCell {

    @IBOutlet weak var ivCat: UIImageView!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func prepare(with cat: Cat) {
        if let photoData = cat.image {
            ivCat.image = UIImage(data: photoData)
        }
        
        self.backgroundColor = UIColor(named: ThemeColorsBackground.allValues[UserDefaultsManager.themeNumber()].rawValue)
        
    }

}
