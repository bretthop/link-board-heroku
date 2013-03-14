module LinksHelper
  def validate(link)
    return 'Link must be provided.' unless link
    return 'Link Group must be provided.' unless link[:link_group_id] && link[:link_group_id].to_s.size() >= 1
    return 'Link Group Id must be a valid number.' unless link[:link_group_id].to_s.match('^[0-9]+$')
    return 'Title must be provided.' unless link[:title] && link[:title].size >= 1
    return 'Href must be provided.' unless link[:href] && link[:href].size >= 1

    nil
  end
end
